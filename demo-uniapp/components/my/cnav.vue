<template>
	<view class="cnav" :style="{ 'background-color': bg }">
		<view class="cnav-item" @click="cleftClick()">
			<image :src="leftIcon" v-show="showLeft"></image>
		</view>
		<view class="text">{{ text }}</view>
		<view class="cnav-item" @click='crightClick()'>
			<image :src="rightIcon" v-show="showRight"></image>
		</view>
	</view>
</template>

<script>
	export default {
		name: 'cnav',

		props: {
			bg: {
				type: String,
				default: '#000000'
			},
			text: {
				type: String,
				default: ''
			},

			showLeft: {
				type: Boolean,
				default: true
			},

			leftIcon: {
				type: String,
				default: '/static/back-white.png'
			},

			cleft: {
				type: Boolean,
				default: false
			},

			showRight: {
				type: Boolean,
				default: true
			},

			rightIcon: {
				type: String,
				default: '/static/reload.png'
			},

			cright: {
				type: Boolean,
				default: false
			}
		},
		methods: {
			cleftClick() {
				if (this.cleft) {
					this.$emit('leftClick')
				} else {
					uni.navigateBack({

					})
				}
			},
			crightClick() {
				if (this.cright) {
					this.$emit('rightClick')
				}
			}
		}
	}
</script>

<style lang="scss" scoped>
	.cnav {
		display: flex;
		width: 750upx;
		height: 70upx;
		flex-direction: row;

		.cnav-item {
			width: 100upx;
			height: 70upx;
			display: flex;
			justify-content: center;
			align-items: center;

			image {
				width: 50upx;
				height: 50upx;
			}
		}

		.text {
			width: 550upx;
			height: 70upx;
			display: flex;
			color: #ffffff;
			justify-content: center;
			align-items: center;
			align-content: center;
			font-size: 35upx;
		}
	}
</style>
