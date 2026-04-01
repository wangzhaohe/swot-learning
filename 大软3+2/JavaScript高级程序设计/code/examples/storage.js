// @ts-check
/**
 * storage.js
 * - Storage 工具类 - 支持对象序列化、过期时间与容量溢出处理
 * - 导出 local 和 session
 */
class StorageWrapper {
    /**
     * 初始化 Storage 包装器
     * @param {Storage} storage - 浏览器原生存储对象 (localStorage 或 sessionStorage)
     */
    constructor(storage) {
        this.storage = storage;
    }
    /**
     * 设置值
     * @param {string} key 键名
     * @param {any} value 值（自动序列化）
     * @param {number | null} expire 过期时间（分钟）
     * @returns {boolean} 是否成功写入存储
     */
    set(key, value, expire = null) {
        const data = {
            value: value,
            timestamp: Date.now(),
            expire: expire ? Date.now() + expire * 60 * 1000 : null
        };
        try {
            this.storage.setItem(key, JSON.stringify(data));
            return true;
        }
        catch (e) {
            // 如果存储空间满了，尝试清理过期数据后重试
            if (/** @type {any} */(e).name === 'QuotaExceededError') {
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
     * @param {string} key 键名
     * @param {any} defaultValue 默认值
     * @returns {any} 存储的值或默认值
     */
    get(key, defaultValue = null) {
        try {
            const item = this.storage.getItem(key);

            if (!item)
                return defaultValue;

            const data = JSON.parse(item);

            // 过期判断
            if (data.expire && Date.now() > data.expire) {
                this.storage.removeItem(key);
                return defaultValue;
            }
            return data.value;
        }
        catch (e) {
            return defaultValue;
        }
    }
    /**
     * 从存储中移除指定键的数据项。
     * @param {string} key - 要移除的数据项的键名。
     * @returns {void}
     */
    remove(key) {
        this.storage.removeItem(key);
    }

    // 这个方法没有被用上，白定义了
    clear() {
        this.storage.clear();
    }
    clearExpired() {
        const now = Date.now();
        // 建议：先获取所有 key 的快照，避免删除元素导致索引错乱
        const keys = Object.keys(this.storage);

        for (const key of keys) {
            /** @type {string | null} */
            const rawData = this.storage.getItem(key);

            // 进行非空判断，JSDoc/TS 会自动推断出此处 rawData 必为 string
            if (rawData !== null) {
                try {
                    const data = JSON.parse(rawData);
                    if (data && data.expire && now > data.expire) {
                        this.storage.removeItem(key);
                    }
                } catch (e) {
                    // 忽略解析失败的情况
                }
            }
        }
    }
}

// 使用类的构造器实例化对象后，导出实例（方便直接导入使用）
export const local = new StorageWrapper(localStorage);
export const session = new StorageWrapper(sessionStorage);
