<template>
	<view class="content">
		<top></top>
		<cnav text='首页-商品行情' :showLeft='false' :cright='true' @rightClick='getData()'></cnav>
		<scroll-view class="list-box" scroll-y="" v-if="productList.length != 0">
			<view v-for="(product,inx) in productList" :key='inx' class="list-item product-item" @tap="buy(product)">
				<view class="product-item-cont">
					<view class="icon">
						{{ product.symbol }}
					</view>
					<view class="name">
						{{ product.name }}
					</view>
					<view class="right">
						<view class="right-item" style="color: #FF5053;">
							{{ product.max | dealMax}}
						</view>
						<view class="right-item">
							最高盈利：96%
						</view>
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
				productList: []
			}
		},

		onLoad() {
			that = this;
			that.getData();
		},

		methods: {
			getData() {
				that.$utils.http(that.$api.product_list, function(res) {
					that.productList = res;
				});
			},

			buy(product) {
				uni.navigateTo({
					url: '/pages/product/buy?sid=' + product.sid
				})
			}
		},
		filters:{
			dealMax:function(max){
				return Math.floor((max + Math.random()) * 10000) / 10000;
			}
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		background-color: #0E1016;

		.product-item {
			height: 150upx;
			width: 750upx;
			border-bottom: 1upx solid #555555;
			display: flex;
			justify-content: center;

			.product-item-cont {
				width: 720upx;
				height: 150upx;
				display: flex;
				flex-direction: row;
				justify-content: space-between;
				align-items: center;
				color: #F8F8F8;

				.icon {
					width: 120upx;
					height: 120upx;
					border-radius: 50%;
					display: flex;
					justify-content: center;
					align-items: center;
					background-color: #CC00FF;
					font-weight: 900;
				}

				.name {
					width: 220upx;
					height: 150upx;
					display: flex;
				}

				.right {
					width: 340upx;
					height: 150upx;
					display: flex;
					flex-direction: column;
					font-size: 32upx;
					display: flex;
					justify-content: flex-end;


					.right-item {
						width: 340upx;
						height: 75upx;
						display: flex;
						justify-content: flex-end;
					}

				}

			}

		}

	}
</style>
