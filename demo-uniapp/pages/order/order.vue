<template>
	<view class="content">
		<top></top>
		<cnav text='交易记录' :showLeft='false' :showRight='true' :cright='true' @rightClick='getData()'></cnav>
		<view class="switch-pane">
			<view class="pane-item" v-bind:class="{'pane-active':paneInx == 0}" @tap="changePaneInx(1)">
				持仓记录
			</view>
			<view class="pane-jg"></view>
			<view class="pane-item" v-bind:class="{'pane-active':paneInx == 1}" @tap="changePaneInx(0)">
				历史记录
			</view>
		</view>
		<scroll-view class="list-box" scroll-y="true" v-if="paneInx == 0">
			<orderItem v-for="(order,inx) in orderList1" :key='inx' :order='order' :widthBorder='false'>
				
			</orderItem>
		</scroll-view>

		<scroll-view class="list-box" scroll-y="true" v-if="paneInx == 1">
			<orderItem v-for="(order,inx) in orderList2" :key='inx' :order='order'>
				
			</orderItem>
		</scroll-view>
	</view>
</template>

<script>
	var that;

	import orderItem from '@/components/my/order-item.vue'

	export default {

		components: {
			orderItem
		},

		data() {
			return {
				paneInx: '0',
				orderList1: [],
				orderList2: []
			}
		},

		onLoad() {
			that = this;
			that.getData();
		},

		methods: {
			changePaneInx(inx) {
				that.paneInx = (that.paneInx + 1) % 2;
			},

			getData() {
				that.$utils.http(that.$api.order_list, function(res) {
					that.orderList1 = res.NOT_SUCCESS;
					that.orderList2 = res.ALREADY_SUCCESS;
				});
			}
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		background-color: #474747;

		.switch-pane {
			height: 60upx;
			width: 750upx;
			display: flex;
			flex-direction: row;
			align-items: center;
			justify-content: space-between;
			border-bottom: 2upx solid #000000;

			.pane-jg {
				width: 3upx;
				height: 40upx;
				background-color: #000000;
			}

			.pane-item {
				height: 60upx;
				width: 370upx;
				display: flex;
				align-items: center;
				justify-content: center;
				font-size: 34upx;
				color: #F1F1F1;
			}

			.pane-active {
				color: #FAB116;
				border-bottom: 2upx solid #FAB116;
			}
		}
	}
</style>
