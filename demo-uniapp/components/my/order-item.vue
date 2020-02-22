<template>

	<view class="order-item-box">
		<view class="order-item-cont">
			<view class="order-item-left">
				<view class="left-top">
					<view class="product-name">
						{{order.productName}}
					</view>
					<view class="buy-inx">
						{{order | dealBuyInx}}
					</view>
				</view>

				<view class="left-item">
					{{order.price}}
				</view>

				<view class="left-item">
					{{order.createTime}}
				</view>

			</view>
			<view class="order-item-right">
				{{order.money}}
			</view>

		</view>

		<view class="sub-process" v-show="widthBorder">
			<view class="sub-process-left" :style="{width:'250upx'}"></view>
			<view class="sub-process-right" :style="{width:'500upx'}"></view>
		</view>

		<view class="sub-process" v-show="!widthBorder">
			<view class="sub-process-left" :style="{width:'750upx'}"></view>
		</view>
	</view>

</template>

<script>
	var that;
	export default {

		name: 'orderItem',

		data() {
			return {
				leftWidth: '',
				rightWidth: ''
			}
		},

		props: {
			order: {},
			widthBorder: {}
		},

		mounted() {
			this.init();
			that = this;
		},

		methods: {
			init() {
				let currentTime = new Date().getTime();
				let endTime = this.order.endTime;
			}
		},
		filters: {
			dealBuyInx(o) {
				var b = o.inx == 1 ? '买涨' : '买跌';
				return b + '(' + o.orderMoney +  ')';
			}
		}
	}
</script>

<style lang="scss" scoped>
	.order-item-box {
		height: 153upx;
		width: 750upx;
		display: flex;
		flex-direction: column;
		align-items: center;

		.order-item-cont {
			height: 150upx;
			width: 720upx;
			display: flex;
			flex-direction: row;

			.order-item-left {
				width: 500upx;
				height: 150upx;
				display: flex;
				flex-direction: column;

				.left-top {
					width: 500upx;
					height: 80upx;
					display: flex;
					flex-direction: row;

					.product-name {
						font-size: 40upx;
						color: #FFFFFF;
						width: 250upx;
						display: flex;
						align-items: center;
						justify-content: flex-start;
					}

					.buy-inx {
						font-size: 32upx;
						color: #FF5053;
						width: 250upx;
						display: flex;
						align-items: center;
						justify-content: center;
					}
				}
				
				.left-item{
					width: 500upx;
					height: 35upx;
					font-size: 26upx;
					display: flex;
					align-items: center;
				}
			}
			
			.order-item-right{
				width: 220upx;
				height: 150upx;
				display: flex;
				align-items: center;
				justify-content: center;
				color: #FF5053;
				font-size: 60upx;
			}
		}

		.sub-process {
			width: 750upx;
			height: 3upx;
			display: flex;
			flex-direction: row;

			.sub-process-left {
				background: #F8F8F8;
			}

			.sub-process-right {
				background: #FAB116;
			}

		}
	}
</style>
