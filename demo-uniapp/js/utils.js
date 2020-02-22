import store from '@/js/store.js'
import constant from '@/js/constant.js'
import api from '@/js/api.js'

const http = function(url, data, success, options) {
	
	uni.showLoading({
		title: '请求中...',
		mask: true
	});

	var type = typeof data === 'function';
	if (type) {
		options = success;
		success = data;
		data = {};
	}

	options = options || {};
	
	return uni.request({
		url: constant.server_url + url,
		header: {
			Authorization: store.getToken(),
			uniapp: 'true'
		},
		data: data,
		method: options.type || 'post',
		dataType: options.dataType || 'json',
		success: function(res) {		
			uni.hideLoading();
			var code = res.data.code;
			var data = res.data.data;

			if (code === 0) {
				success && success(data);
			} else if (code === 399) {
				uni.showModal({
					content: data || '登陆过期或未登录，请先登陆再进行操作',
					showCancel: false,
					success: function(res) {
						uni.reLaunch({
							url: '/pages/login'
						})
					}
				});
			} else if (code === 401) {
				uni.showModal({
					content: data || '暂无权限获取此数据,请与管理员联系',
					showCancel: false
				});
			} else {
				uni.showModal({
					content: data ||'服务器繁忙,请重试',
					showCancel: false
				});
			}

		},
		fail: function(e){
			
			console.log(e)
			
			uni.hideLoading();
			uni.showModal({
				content: '服务器繁忙，请重试',
				showCancel: false
			});
		}

	})
}

const readJson = function(filename,success){
	
	return uni.request({
		url: constant.server_url + api.kdata + filename,
		method: 'get',
		dataType: 'html',
		success: function(res) {
			success && success(res.data);
		},
		fail: function() {
			uni.hideLoading();
			uni.showModal({
				content: '服务器繁忙，请重试',
				showCancel: false
			});
		}
	})
}



export default {
	http,
	readJson
}
