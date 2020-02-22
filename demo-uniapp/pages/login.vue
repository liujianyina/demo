<template>
	<view class="content">
		<view class="top"></view>

		<view class="logo">
			<image :src="o.url" mode=""></image>
		</view>

		<view class="name">
			{{o.app_name}}
		</view>

		<view class="small-name">
			{{o.app_small_name}}
		</view>

		<view class="login-box">
			<view class="login-box-item">
				<view class="login-box-item-cont">
					<view class="icon">
						<image src="/static/username.png" mode=""></image>
					</view>
					<view class="input-box">
						<input type="text" v-model="username" placeholder="用户名/username" />
					</view>
				</view>
			</view>

			<view class="login-box-item">
				<view class="login-box-item-cont">
					<view class="icon">
						<image src="/static/password.png" mode=""></image>
					</view>
					<view class="input-box">
						<input type="password" v-model="password" placeholder="密码/password" />
					</view>
				</view>
			</view>
		</view>

		<view class="sub-jg"></view>

		<view class="sub-item">
			<button type="primary" class="submit-btn" @tap="login()">登 陆/SIGN IN</button>
		</view>
		<view class="sub-item" style="margin-top: 30upx;">
			<navigator url="/pages/register">创建账号/New Account</navigator>
		</view>
		<view class="sub-item">
			<navigator url="">忘记密码/Frogetten Password?</navigator>
		</view>
		<view class="sub-item">
			<navigator url="">APP下载</navigator>
		</view>
	</view>
</template>

<script>
	var that;
	export default {
		data() {
			return {
				username: '',
				password: '',
				o: {}
			}
		},
		onLoad() {
			that = this;
			that.getData();
		},
		methods: {
			login() {
				that.$utils.http(that.$api.login, {
					username: that.username,
					password: that.password
				}, function(res) {
					that.$store.setToken(res);

					uni.switchTab({
						url: '/pages/product/product'
					})
				})
			},
			getData() {
				that.$utils.http(that.$api.customer_service_url, function(res) {
					res.url = that.dealUrl(res.url);
					that.o = res;
				});
			},
			dealUrl(u){
				var u = that.$constant.server_url + '/jsons/' + u;
				return u;
			}
		}
	}
</script>

<style lang="scss" scoped>
	.content {

		background-image: url(https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1428254237,2071131444&fm=26&gp=0.jpg);

		.top {
			height: 200upx;
			width: 750upx;
			display: flex;
		}

		.logo {
			height: 150upx;
			width: 750upx;
			display: flex;
			justify-content: center;

			image {
				height: 150upx;
				width: 150upx;
				border-radius: 50%;
			}
		}

		.name {
			width: 750upx;
			display: flex;
			justify-content: center;
			color: #FFFFFF;
			font-weight: 1000;
			font-size: 50upx;
			height: 70upx;
		}

		.small-name {
			width: 750upx;
			display: flex;
			justify-content: center;
			color: #FFFFFF;
			font-size: 14upx;
			height: 20upx;
		}

		.login-box {
			width: 750upx;
			margin-top: 50upx;
		}

		.login-box-item {
			height: 70upx;
			width: 750upx;
			margin-top: 40upx;
			display: flex;
			justify-content: center;

			.login-box-item-cont {
				width: 500upx;
				height: 80upx;
				display: flex;
				flex-direction: row;
				background: rgba($color: #FFFFFF, $alpha: 0.1);
				border-radius: 20upx;

				.icon {
					width: 70upx;
					height: 80upx;
					display: flex;
					justify-content: center;
					align-items: center;

					image {
						width: 40upx;
						height: 40upx;
					}
				}

				.input-box {
					width: 430upx;
					height: 80upx;

					input {
						width: 430upx;
						height: 80upx;
						color: #FFFFFF;
						font-size: 25upx;
					}
				}
			}
		}


		.sub-jg {
			height: 100upx;
			width: 750upx;
		}

		.sub-item {
			height: 100upx;
			width: 750upx;
			color: #FAB116;
			display: flex;
			justify-content: center;
			align-items: center;
			font-size: 30upx;
		}

		.submit-btn {
			height: 80upx;
			width: 500upx;
			background-color: #FAB116;
			color: #000000;
			display: flex;
			align-items: center;
			justify-content: center;
			font-size: 30upx;
		}
	}
</style>
