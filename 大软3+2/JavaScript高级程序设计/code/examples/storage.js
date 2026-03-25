/**
 * storage.js
 * Storage 工具类 - 支持对象序列化、过期时间与容量溢出处理
 */
class StorageWrapper {
    constructor(storage) {
        this.storage = storage;
    }
    /**
     * 设置值
     * @param {string} key 键名
     * @param {any} value 值（自动序列化）
     * @param {number} expire 过期时间（分钟）
     */
    set(key, value, expire = null) {
        try {
            const data = {
                value: value,
                timestamp: Date.now(),
                expire: expire ? Date.now() + expire * 60 * 1000 : null
            };
            this.storage.setItem(key, JSON.stringify(data));
            return true;
        } catch (e) {
            // 如果存储空间满了，尝试清理过期数据后重试
            if (e.name === 'QuotaExceededError') {
                console.warn('Storage space full, clearing expired items...');
                this.clearExpired();
                try {
                    this.storage.setItem(key, JSON.stringify(data));
                    return true;
                } catch (retryError) {
                    console.error('Storage still full after clearing:', retryError);
                    return false;
                }
            }
            return false;
        }
    }
    /**
     * 获取值
     */
    get(key, defaultValue = null) {
        try {
            const item = this.storage.getItem(key);
            if (!item) return defaultValue;

            const data = JSON.parse(item);

            // 过期判断
            if (data.expire && Date.now() > data.expire) {
                this.storage.removeItem(key);
                return defaultValue;
            }
            return data.value;
        } catch (e) {
            return defaultValue;
        }
    }
    remove(key) {
        this.storage.removeItem(key);
    }
    clear() {
        this.storage.clear();
    }
    clearExpired() {
        const now = Date.now();
        for (let i = 0; i < this.storage.length; i++) {
            const key = this.storage.key(i);
            try {
                const data = JSON.parse(this.storage.getItem(key));
                if (data.expire && now > data.expire) {
                    this.storage.removeItem(key);
                }
            } catch (e) { /* 忽略非工具类存入的普通字符串 */ }
        }
    }
}

// 导出实例（方便直接导入使用）
export const local = new StorageWrapper(localStorage);
export const session = new StorageWrapper(sessionStorage);
