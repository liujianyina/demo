<template>
	<view class="content">
		<top></top>
		<cnav :text='product.name' :showLeft='true' :showRight='false'></cnav>
		<view class="buy-top">
			<view class="one">
				{{current}}
			</view>
			<view class="top-item">
				<view class="top-item-title">
					开盘
				</view>
				<view class="top-item-value">
					{{open}}
				</view>
			</view>
			<view class="top-item">
				<view class="top-item-title">
					最高
				</view>
				<view class="top-item-value">
					{{product.max}}
				</view>
			</view>
			<view class="top-item">
				<view class="top-item-title">
					最低
				</view>
				<view class="top-item-value">
					{{product.min}}
				</view>
			</view>
		</view>
		<view class="action">
			<view class="action-one" @tap="change()">
				<view class="action-one-box">
					<view class="action-one-box-in">
						<view class="action-one-box-item" v-bind:class="{active:inx1 == 0}">
							K线
						</view>
						<view class="action-one-box-item" v-bind:class="{active:inx1 == 1}">
							趋势
						</view>
					</view>
				</view>
			</view>
			<view class="action-item" v-bind:class="{'with-border':inx2 == 1}" @tap="changeInx2(1,5)">1M</view>
			<view class="action-item" v-bind:class="{'with-border':inx2 == 2}" @tap="changeInx2(2,10)">5M</view>
			<view class="action-item" v-bind:class="{'with-border':inx2 == 3}" @tap="changeInx2(3,20)">15M</view>
			<view class="action-item" v-bind:class="{'with-border':inx2 == 4}" @tap="changeInx2(4,50)">30M</view>
			<view class="action-item" v-bind:class="{'with-border':inx2 == 5}" @tap="changeInx2(5,100)">1H</view>
			<view class="action-item" v-bind:class="{'with-border':inx2 == 6}" @tap="changeInx2(6,200)">1D</view>
		</view>

		<view class="k-box">
			<canvas canvas-id="canvasCandle" id="canvasCandle" class="charts" disable-scroll=true @touchstart="touchCandle"
			 @touchmove="moveCandle" @touchend="touchEndCandle"></canvas>
		</view>

		<view class="sub-box">
			<view class="ping sub-item-common">
				<view class="sub-common-item">
					<image src="/static/ping.png" mode=""></image>
				</view>
				<view class="sub-common-item">
					平仓
				</view>
			</view>
			<view class="up sub-box-item sub-item-common" @tap="buy(1)">
				<view class="sub-common-item">
					<image src="/static/up.png" mode=""></image>
				</view>
				<view class="sub-common-item">
					买涨
				</view>
			</view>
			<view class="down sub-box-item sub-item-common" @tap="buy(2)">
				<view class="sub-common-item">
					<image src="/static/down.png" mode=""></image>
				</view>
				<view class="sub-common-item">
					买跌
				</view>
			</view>
		</view>

		<uni-popup ref="buy" type="center" mask-click="true">
			<view class="buy-box">
				<view class="buy-box-title">
					订单确认
				</view>
				<view class="time-item">
					到期时间：
				</view>
				<view class="time-item time">
					{{endTime | dealDate}}
				</view>
				<scroll-view class="jie-time" scroll-x="true">
					<view class="jie-time-item" v-bind:class="{'with-border':time == 60}" @tap="changeTime(60,1.3)">
						<view class="jie-time-item-title">
							结算时间
						</view>
						<view class="jie-time-item-time">
							60秒
						</view>
						<view class="jie-time-item-sy">
							收益30%
						</view>
					</view>
					<view class="jie-time-item" v-bind:class="{'with-border':time == 120}" @tap="changeTime(120,1.4)">
						<view class="jie-time-item-title">
							结算时间
						</view>
						<view class="jie-time-item-time">
							120秒
						</view>
						<view class="jie-time-item-sy">
							收益40%
						</view>
					</view>
					<view class="jie-time-item" v-bind:class="{'with-border':time == 180}" @tap="changeTime(180,1.5)">
						<view class="jie-time-item-title">
							结算时间
						</view>
						<view class="jie-time-item-time">
							180秒
						</view>
						<view class="jie-time-item-sy">
							收益50%
						</view>
					</view>
					<view class="jie-time-item" v-bind:class="{'with-border':time == 300}" @tap="changeTime(300,1.6)">
						<view class="jie-time-item-title">
							结算时间
						</view>
						<view class="jie-time-item-time">
							300秒
						</view>
						<view class="jie-time-item-sy">
							收益60%
						</view>
					</view>
				</scroll-view>
				<view class="time-item">
					投资金额：
				</view>

				<view class="money-box">
					<scroll-view class="money-scroll" scroll-x="">
						<view class="money-item" v-bind:class="{'with-border':money == 20}" @tap="changeMoney(20)">
							$20
						</view>
						<view class="money-item" v-bind:class="{'with-border':money == 50}" @tap="changeMoney(50)">
							$50
						</view>
						<view class="money-item" v-bind:class="{'with-border':money == 100}" @tap="changeMoney(100)">
							$100
						</view>
						<view class="money-item" v-bind:class="{'with-border':money == 200}" @tap="changeMoney(200)">
							$200
						</view>
						<view class="money-item" v-bind:class="{'with-border':money == 500}" @tap="changeMoney(500)">
							$500
						</view>
					</scroll-view>
					<view class="other">
						<view class="other-cont">
							<input type="number" @input='otherMoney' placeholder="其他" />
						</view>
					</view>
				</view>
				<view class="account-msg">
					<view class="msg-item">
						余额：${{user.money}}
					</view>
					<view class="msg-item">
						手续费：$0%
					</view>
				</view>

				<view class="order-msg">
					<view class="order-item">
						<view class="order-msg-title">
							名称
						</view>
						<view class="order-msg-cont">
							{{product.name}}
						</view>
					</view>
					<view class="order-item">
						<view class="order-msg-title">
							方向
						</view>
						<view class="order-msg-cont with-red">
							{{buyInx == 1? '买涨' : '买跌'}}
						</view>
					</view>
					<view class="order-item">
						<view class="order-msg-title">
							现价
						</view>
						<view class="order-msg-cont with-red">
							{{current}}
						</view>
					</view>
					<view class="order-item">
						<view class="order-msg-title">
							金额
						</view>
						<view class="order-msg-cont">
							{{money}}
						</view>
					</view>
				</view>

				<view class="submit-box">
					<button type="primary" @tap="createOrder()">确认下单</button>
				</view>

				<view class="yu-in">
					预期收益：${{money * bili}}
				</view>

			</view>
		</uni-popup>

		<uni-popup ref="res" type="center" maskClick="ture">
			<view class="res-box">
				<view class="res-box-title">
					{{product.name}}
				</view>
				<view class="djs">
					<view class="djs-box" v-if="inx3 == 0">
						<canvas canvas-id="canvasArcbar1" id="canvasArcbar1" class="charts3"></canvas>
					</view>
					<view class="money-box" v-if="inx3 == 1">
						$ {{ orderRes.money }}
					</view>
				</view>
				<view class="order-msg">
					<view class="order-item">
						<view class="order-msg-title">
							名称
						</view>
						<view class="order-msg-cont">
							{{product.name}}
						</view>
					</view>
					<view class="order-item">
						<view class="order-msg-title">
							方向
						</view>
						<view class="order-msg-cont with-red">
							{{buyInx == 1? '买涨' : '买跌'}}
						</view>
					</view>
					<view class="order-item">
						<view class="order-msg-title">
							现价
						</view>
						<view class="order-msg-cont with-red">
							{{current}}
						</view>
					</view>
					<view class="order-item">
						<view class="order-msg-title">
							金额
						</view>
						<view class="order-msg-cont">
							{{money}}
						</view>
					</view>
				</view>
				<view class="submit-box">
					<button type="primary" @tap="jxbuy()">继续下单</button>
				</view>
			</view>
		</uni-popup>
	</view>
</template>

<script>
	var that;
	var canvaCandle = null;
	var canvaArcbar1 = null;
	var timeoutID = null;
	var djsFunction = null;


	import uniPopup from '@/components/uni-popup/uni-popup.vue'
	import uCharts from '@/components/u-charts/u-charts.js';

	export default {
		components: {
			uniPopup
		},
		data() {
			return {
				sid: '',
				product: {},
				user: {},
				order: {},
				orderRes: {},

				open: '0',
				current: '0',

				inx1: '0',
				inx2: '1',
				inx3: '0', //0:展示倒计时  1:展示订单

				time: 60,
				money: '20',
				buyInx: '',
				bili: 1.3,
				endTime: '',
				djs: '',

				itemCount: 100
			}
		},

		onLoad(options) {
			that = this;
			that.sid = options.sid;

			that.getProduct();
			that.getUser();
			that.getEndTime();

			setInterval(function() {
				let timestamp = that.endTime.getTime(); //当前的时间戳
				timestamp = timestamp + 1000;
				that.endTime = new Date(timestamp);
			}, 1000);

			setInterval(function() {
				that.current = that.getRandom();
			}, 1000);


		},

		methods: {

			getProduct() {
				that.$utils.http(that.$api.product_info, {
					sid: that.sid
				}, function(res) {
					that.product = res;

					that.current = that.getRandom();
					that.open = that.getRandom();
					that.getServerData();
				});
			},

			getUser() {
				that.$utils.http(that.$api.get_user_info, function(res) {
					that.user = res;
				})
			},

			getServerData() {
				that.$utils.readJson(that.product.filename, function(res) {
					that.showCharts(eval(res));
				});
			},

			showCharts(res) {

				let info = uni.createSelectorQuery().select(".k-box");
				info.boundingClientRect(function(data) { //data - 各种参数

					var times = res.map(function(item) {
						return item[0];
					});

					var ydata = res.map(function(item) {
						return [+item[1], +item[2], +item[5], +item[6]];
					});

					var chartData = {
						categories: times,
						series: [{
							name: that.product.name,
							data: ydata
						}]
					};

					canvaCandle = new uCharts({
						$this: that,
						canvasId: 'canvasCandle',
						type: 'candle',
						fontSize: 11,
						legend: {
							show: true
						},
						background: '#FFFFFF',
						pixelRatio: 1,
						categories: chartData.categories,
						series: chartData.series,
						enableScroll: true,
						animation: true,
						xAxis: {
							disableGrid: true,
							itemCount: that.itemCount,
							scrollShow: true,
							scrollAlign: 'right',
							fontColor:'#FFFFFF'
						},
						yAxis: {
							disableGrid: true,
							gridType: 'dash',
							splitNumber: 5,
							data:{
								// fontColor:'#FFFFFF',
							}
						},
						
						width: data.width,
						height: data.height,
						dataLabel: false,
						dataPointShape: true,
						extra: {
							candle: {
								color: {
									upLine: '#f04864',
									upFill: '#f04864',
									downLine: '#2fc25b',
									downFill: '#2fc25b'
								},
								average: {
									show: true,
									name: ['MA5', 'MA10', 'MA30'],
									day: [5, 10, 30],
									color: ['#1890ff', '#2fc25b', '#facc14']
								}
							},
							tooltip: {
								bgColor: '#000000',
								bgOpacity: 0.7,
								gridType: 'dash',
								dashLength: 5,
								gridColor: '#1890ff',
								fontColor: '#1890ff',
								horizentalLine: false,
								labelBgColor: '#DFE8FF',
								labelBgOpacity: 0.95,
								labelAlign: 'left',
								labelFontColor: '#666666'
							}
						},
					});

				}).exec();
			},

			createOrder() {
				that.$utils.http(that.$api.create_order, {
					name: that.product.name,
					time: that.time,
					money: that.money,
					price: that.current,
					inx: that.buyInx
				}, function(res) {
					that.order = res;
					that.$refs.buy.close();
					that.$refs.res.open();

					that.orderRes = res;

					that.inx3 = 0;
					that.djs = that.time;
					that.showDjs();
				});
			},

			showDjs() {

				var bfb = that.djs / that.time;

				var chartData = {
					series: [{
						data: bfb,
						color: '#2fc25b'
					}]
				}

				canvaArcbar1 = new uCharts({
					$this: that,
					canvasId: 'canvasArcbar1',
					type: 'arcbar',
					fontSize: 11,
					legend: {
						show: false
					},
					background: '#FFFFFF',
					pixelRatio: 1,
					series: chartData.series,

					width: uni.upx2px(200),
					height: uni.upx2px(200),
					dataLabel: true,
					title: {
						name: that.djs, //这里我自动计算了，您可以直接给任意字符串
						color: chartData.series[0].color,
						fontSize: 25
					},
					subtitle: {
						name: chartData.series[0].name, //这里您可以直接给任意字符串
						color: '#666666',
						fontSize: 15
					},
					extra: {
						arcbar: {
							type: 'circle', //整圆类型进度条图
							width: uni.upx2px(24), //圆弧的宽度
							startAngle: 0.5 //整圆类型只需配置起始角度即可
						}
					}
				});
				if (that.djs != '0') {
					djsFunction = setTimeout(function() {
						that.djs--;
						that.showDjs();
					}, 1000)
				} else {

					clearTimeout(djsFunction);

					that.inx3 = 1;
					that.$utils.http(that.$api.order_info, {
						sid: that.orderRes.sid
					}, function(res) {
						that.orderRes = res;
					})
				}
			},

			changeMoney(money) {
				that.money = money;
			},

			otherMoney(event) {
				that.money = event.target.value
			},

			getEndTime() {
				let timestamp = new Date().getTime(); //当前的时间戳
				timestamp = timestamp + 1000 * that.time;
				that.endTime = new Date(timestamp);
			},

			changeTime(time, bili) {
				that.time = time;
				that.bili = bili;
				that.getEndTime();
			},

			buy(buyInx) {
				that.buyInx = buyInx;
				that.$refs.buy.open();
			},

			jxbuy() {
				that.$refs.res.close();
				that.$refs.buy.open();
				clearTimeout(djsFunction);
			},

			change(inx) {
				that.inx1 = (that.inx1 + 1) % 2;
			},

			changeInx2(inx2, itemCount) {
				that.inx2 = inx2;
				that.itemCount = itemCount;

				canvaCandle.zoom({
					itemCount: itemCount
				});

			},

			getRandom() {
				var random = Math.random() * (that.product.max - that.product.min) + that.product.min;
				return Math.floor(random * 100000) / 100000;
			},

			touchCandle(e) {
				canvaCandle.scrollStart(e);
			},
			moveCandle(e) {
				canvaCandle.scroll(e);
			},
			touchEndCandle(e) {
				canvaCandle.scrollEnd(e);
				//下面是toolTip事件，如果滚动后不需要显示，可不填写
				canvaCandle.showToolTip(e, {
					format: function(item, category) {
						return category + ' ' + item.name + ':' + item.data
					}
				});
			}
		}

	}
</script>

<style lang="scss" scoped>
	.buy-top {
		height: 140upx;
		width: 750upx;
		background: #1C1C1F;
		display: flex;
		flex-direction: row;
		color: #F41234;

		.one {
			width: 300upx;
			height: 140upx;
			font-size: 60upx;
			display: flex;
			align-items: center;
		}

		.top-item {
			width: 150upx;
			height: 140upx;
			display: flex;
			flex-direction: column;

			.top-item-title {
				width: 150upx;
				height: 60upx;
				font-size: 34upx;
				color: #F8F8F8;
				display: flex;
				align-items: center;
			}

			.top-item-value {
				width: 150upx;
				height: 80upx;
				font-size: 32upx;
				display: flex;
				align-items: center;
			}
		}
	}

	.action {
		height: 100upx;
		width: 750upx;
		background-color: #474747;
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: center;

		.action-one {
			height: 100upx;
			width: 240upx;
			display: flex;
			align-items: center;
			justify-content: center;

			.action-one-box {
				height: 70upx;
				width: 220upx;
				background-color: #000000;
				border-radius: 20upx;
				display: flex;
				align-items: center;
				justify-content: center;

				.action-one-box-in {
					width: 210upx;
					height: 60upx;
					border-radius: 20upx;
					display: flex;
					flex-direction: row;

					.action-one-box-item {
						width: 105upx;
						height: 60upx;
						display: flex;
						align-items: center;
						justify-content: center;
						color: #FFFFFF;
						border-radius: 20upx;
						font-size: 28upx;
					}

					.active {
						background-color: #474747;
						color: #FAB116;
					}
				}
			}


		}

		.action-item {
			width: 85upx;
			height: 90upx;
			display: flex;
			align-items: center;
			justify-content: center;
			font-size: 30upx;
			color: #F8F8F8;
		}

		.with-border {
			border-bottom: 2upx solid #FAB116;
		}
	}


	.k-box {
		display: flex;
		flex-grow: 1;
		width: 750upx;

		.charts {
			width: 750upx;
			height: 1100upx;
		}
	}

	.sub-box {
		display: flex;
		flex-direction: row;
		width: 750upx;
		height: 120upx;

		.ping {
			width: 300upx;
			background-color: #474747;
		}


		.up {
			background-color: #ff0000;
		}


		.down {
			background-color: #00ff00;
		}

		.sub-box-item {
			width: 225upx;
		}

		.sub-item-common {
			display: flex;
			justify-content: center;
			align-items: center;
			flex-direction: column;

			.sub-common-item {
				height: 50upx;
				font-size: 26upx;
				color: #F8F8F8;

				image {
					height: 50upx;
					width: 50upx;
				}
			}
		}
	}

	.buy-box {
		width: 650upx;
		height: 900upx;
		background-color: #1C1C1F;
		display: flex;
		flex-direction: column;
		align-items: center;
		border-radius: 15upx;

		.buy-box-title {
			height: 80upx;
			width: 620upx;
			border-bottom: 5upx solid #000000;
			display: flex;
			justify-content: center;
			align-items: center;
			color: #FFFFFF;
			font-size: 34upx;
		}

		.time-item {
			width: 620upx;
			height: 70upx;
			display: flex;
			align-items: center;
			justify-content: flex-start;
			color: #9b9b9b;
			font-size: 26upx;
		}

		.time {
			color: #aa55ff;
		}

		.jie-time {
			height: 190upx;
			width: 620upx;
			display: flex;
			align-items: center;
			flex-direction: row;
			white-space: nowrap;
			overflow: hidden;

			.jie-time-item {
				height: 170upx;
				width: 170upx;
				margin-right: 20upx;
				display: inline-block;
				color: #CCCCCC;
				background-color: #474747;
				border-radius: 15upx;

				.jie-time-item-title {
					height: 50upx;
					line-height: 50upx;
					text-align: center;
					font-size: 30upx;
				}

				.jie-time-item-time {
					height: 85upx;
					line-height: 85upx;
					font-size: 46upx;
					color: #FAB116;
					text-align: center;
				}

				.jie-time-item-sy {
					height: 45upx;
					line-height: 45upx;
					font-size: 30upx;
					color: #ff0000;
					text-align: center;
				}
			}
		}

		.money-box {
			height: 90upx;
			width: 620upx;
			display: flex;
			flex-direction: row;
			justify-content: space-between;


			.money-scroll {
				width: 470upx;
				height: 90upx;
				white-space: nowrap;
				line-height: 90upx;
				overflow-x: hidden;

				.money-item {
					height: 70upx;
					width: 120upx;
					margin-right: 20upx;
					display: inline-block;
					color: #FFFFFF;
					background-color: #474747;
					border-radius: 15upx;
					line-height: 70upx;
					text-align: center;
					font-size: 30upx;
				}
			}

			.other {
				width: 140upx;
				height: 90upx;
				padding: 14upx 10upx;

				.other-cont {

					height: 70upx;
					border-radius: 10upx;
					background-color: #474747;
					margin-top: 3upx;
					padding-left: 5upx;
					padding-right: 5upx;

					input {
						width: 110upx;
						height: 70upx;
						color: #FFFFFF;
					}
				}
			}


		}

		.with-border {
			border: 5upx solid #FAB116;
		}

		.account-msg {
			color: #FFFFFF;
			height: 70upx;
			display: flex;
			flex-direction: row;
			font-size: 26upx;
			border-bottom: 5upx solid #000000;

			.msg-item {
				width: 310upx;
				display: flex;
				align-items: center;
			}
		}

		.yu-in {
			height: 60upx;
			width: 620upx;
			display: flex;
			align-items: center;
			justify-content: center;
			color: #FAB116;
			font-size: 26upx;
		}

	}

	.order-msg {
		height: 110upx;
		width: 620upx;
		display: flex;
		flex-direction: row;
		justify-content: space-between;

		.order-item {
			width: 150upx;
			height: 110upx;
			display: flex;
			flex-direction: column;
			font-size: 32upx;

			.order-msg-title {
				width: 150upx;
				height: 50upx;
				display: flex;
				align-items: center;
				justify-content: center;
				color: #F8F8F8;
			}

			.order-msg-cont {
				width: 155upx;
				height: 60upx;
				display: flex;
				align-items: center;
				justify-content: center;
				color: #FFFFFF;
			}

			.with-red {
				color: #F41234;
			}
		}
	}

	.submit-box {
		width: 620upx;
		height: 100upx;
		display: flex;
		align-items: center;
		justify-content: center;

		button {
			width: 620upx;
			height: 85upx;
			background-color: #FAB116;
			display: flex;
			align-items: center;
			justify-content: center;
			border: none;
			border-radius: 15upx;
		}
	}

	.res-box {
		width: 650upx;
		height: 550upx;
		background-color: #1C1C1F;
		display: flex;
		flex-direction: column;
		align-items: center;
		border-radius: 15upx;

		.res-box-title {
			height: 80upx;
			width: 620upx;
			border-bottom: 5upx solid #000000;
			display: flex;
			justify-content: center;
			align-items: center;
			color: #FFFFFF;
			font-size: 34upx;
		}

		.djs {
			width: 620upx;
			height: 240upx;
			border-bottom: 5upx solid #000000;
			display: flex;
			align-items: center;
			justify-content: center;

			.djs-box {
				width: 200upx;
				height: 200upx;
			}

			.money-box {
				width: 500upx;
				height: 200upx;
				display: flex;
				align-items: center;
				justify-content: center;
				color: #FFFFFF;
				font-size: 60upx;
			}
		}
	}
</style>
