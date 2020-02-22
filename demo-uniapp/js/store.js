let TOKEN = 'token';

const setToken = function(token) {
	uni.setStorageSync(TOKEN, token);
}

const getToken = function() {
	return uni.getStorageSync(TOKEN) || '';
}

export default {
	setToken,
	getToken
}
