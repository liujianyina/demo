<template>
	<view class="top" :style="{'background-color':bg}">
	</view>
</template>

<script>
	export default {

		name: 'top',

		props: {
			bg: {
				type: String,
				default: '#000000'
			}
		}
	}
</script>

<style scoped>
	.top {
		height: var(--status-bar-height);
		width: 750upx;
		display: flex;
	}
</style>
