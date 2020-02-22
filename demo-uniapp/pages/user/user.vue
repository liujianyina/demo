<template>
	<view class="content">
		<top></top>
		<cnav text='个人中心' :showLeft='false' :showRight='false'></cnav>
		<view class="user-box">
			<view class="user-img">
				<image src="/static/login-bg.jpg" mode=""></image>
			</view>
			<view class="user-item">
				{{user.username}}
			</view>
			<view class="user-item" style="color: #FAB116;">
				${{user.money}}
			</view>
		</view>
		<view class="x-box">
			<!-- <view class="x-box-item">
				<view class="x-box-item-i">
					<image src="/static/card.png" mode=""></image>
				</view>
				<view class="x-box-item-i">
					签约
				</view>
			</view> -->
			<view class="x-box-jg"></view>
			<view class="x-box-item" @tap="tixian()">
				<view class="x-box-item-i">
					<image src="/static/tixian.png" mode=""></image>
				</view>
				<view class="x-box-item-i">
					用户出金
				</view>
			</view>
			<view class="x-box-jg"></view>
			<view class="x-box-item" @tap="ls()">
				<view class="x-box-item-i">
					<image src="/static/liushui.png" mode=""></image>
				</view>
				<view class="x-box-item-i">
					资金流水
				</view>
			</view>
		</view>
		<scroll-view class="list-box" scroll-y="">

			<navigator url="kefu">
				<view class="list-item sub-item">
					<view class="sub-item-cont">
						<view class="icon">
							<image src="/static/kefu.png" mode=""></image>
						</view>
						<view class="text">
							在线客服
						</view>
						<view class="icon">
							<image src="/static/right.png" mode=""></image>
						</view>
					</view>
				</view>
			</navigator>
			<view class="list-item sub-item" @tap="ls()">
				<view class="sub-item-cont">
					<view class="icon">
						<image src="/static/tixian-2.png" mode=""></image>
					</view>
					<view class="text">
						提现记录
					</view>
					<view class="icon">
						<image src="/static/right.png" mode=""></image>
					</view>
				</view>
			</view>
			<view class="list-item sub-item" @tap="ls()">
				<view class="sub-item-cont">
					<view class="icon">
						<image src="/static/chongzhi.png" mode=""></image>
					</view>
					<view class="text">
						入金记录
					</view>
					<view class="icon">
						<image src="/static/right.png" mode=""></image>
					</view>
				</view>
			</view>
			<!-- <view class="list-item sub-item">
				<view class="sub-item-cont">
					<view class="icon">
						<image src="/static/lishi.png" mode=""></image>
					</view>
					<view class="text">
						历史订单
					</view>
					<view class="icon">
						<image src="/static/right.png" mode=""></image>
					</view>
				</view>
			</view> -->
			<!-- <view class="list-item sub-item">
				<view class="sub-item-cont">
					<view class="icon">
						<image src="/static/xiugai.png" mode=""></image>
					</view>
					<view class="text">
						修改信息
					</view>
					<view class="icon">
						<image src="/static/right.png" mode=""></image>
					</view>
				</view>
			</view> -->
			<view class="list-item sub-item" @tap="logout()">
				<view class="sub-item-cont">
					<view class="icon">
						<image src="/static/tuichu.png" mode=""></image>
					</view>
					<view class="text">
						退出
					</view>
					<view class="icon">
						<image src="/static/right.png" mode=""></image>
					</view>
				</view>
			</view>
		</scroll-view>
	</view>
</template>

<script>
	var that;
	export default {
		data() {
			return {
				user: {}
			}
		},

		onLoad() {
			that = this;
			that.getUser();
		},

		methods: {
			getUser() {
				that.$utils.http(that.$api.get_user_info, function(res) {
					that.user = res;
				})
			},

			logout() {
				that.$store.setToken('');
				uni.reLaunch({
					url: '/pages/login'
				})
			},

			tixian() {
				if (that.user.money <= 0) {
					uni.showModal({
						content: '余额不足，无法提现',
						showCancel: false
					});
				} else {
					that.$utils.http(that.$api.withdraw_money, function(res) {
						// uni.navigateTo({
						// 	url:'tixian'
						// })

						uni.navigateTo({
							url: '/pages/user/tixian?money=' + res
						});
					})
				}
			},
			
			ls(){
				uni.switchTab({
					url: '/pages/order/order'
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		height: 100%;
		background-color: #333333;
		min-height: 100vh;
	}

	.user-box {
		width: 750upx;
		height: 270upx;
		display: flex;
		justify-content: flex-start;
		flex-direction: column;
		background-color: #474747;

		.user-img {
			height: 150upx;
			width: 750upx;
			display: flex;
			justify-content: center;
			align-items: center;

			image {
				width: 120upx;
				height: 120upx;
				border-radius: 50%;
			}
		}

		.user-item {
			width: 750upx;
			height: 60upx;
			display: flex;
			justify-content: center;
			align-items: center;
			color: #FFFFFF;
			font-size: 30upx;
		}
	}

	.x-box {
		width: 750upx;
		height: 140upx;
		background-color: #333333;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;

		.x-box-item {
			width: 245upx;
			height: 140upx;
			display: flex;
			flex-direction: column;
			align-items: center;
			justify-content: center;

			.x-box-item-i {
				width: 200upx;
				height: 70upx;
				display: flex;
				align-items: center;
				justify-content: center;
				font-size: 30upx;
				color: #F8F8F8;

				image {
					width: 100upx;
					height: 70upx;
				}
			}

		}

		.x-box-jg {
			width: 2upx;
			height: 120upx;
			background-color: #000000;
		}
	}
	
	.list-box{
		background-color: #333333;
		flex-grow: 1;
		height: 100%;
	}

	.list-box {
		background-color: #474747;

		.sub-item {
			width: 750upx;
			height: 120upx;
			display: flex;
			justify-content: center;
			border-bottom: 2upx solid #F8F8F8;

			.sub-item-cont {
				width: 720upx;
				height: 120upx;
				display: flex;
				flex-direction: row;

				.icon {
					width: 120upx;
					height: 120upx;
					display: flex;
					align-items: center;
					justify-content: center;

					image {
						width: 60upx;
						height: 60upx;
					}
				}

				.text {
					display: flex;
					align-items: center;
					justify-content: flex-start;
					width: 480upx;
					color: #F8F8F8;
					font-size: 30upx;
				}
			}
		}
	}
</style>
