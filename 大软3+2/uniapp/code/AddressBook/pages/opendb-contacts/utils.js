// 共享工具函数

// 性别文本
export function genderText(gender) {
	switch (gender) {
		case 1: return '男';
		case 2: return '女';
		default: return '未知';
	}
}

// 性别 CSS 类名
export function genderClass(gender) {
	switch (gender) {
		case 1: return 'gender-male';
		case 2: return 'gender-female';
		default: return 'gender-unknown';
	}
}

// 性别选项
export const genderOptions = [
	{ text: '未知', value: 0 },
	{ text: '男', value: 1 },
	{ text: '女', value: 2 },
];
