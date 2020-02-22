<template>

	<view class="cnull">
		<view class="img-box">
			<image src="/static/none.png" mode=""></image>
		</view>
		<view class="msg">
			暂无数据
		</view>
	</view>

</template>

<script>
</script>

<style lang="scss" scoped>
	.cnull {
		display: flex;
		flex-direction: column;
		width: 750upx;
		height: 500upx;
		
		.img-box{
			width: 750upx;
			height: 400upx;
			display: flex;
			justify-content: center;
			align-items: center;
			
			image{
				width: 400upx;
				height: 400upx;
			}
		}
		
		.msg{
			width: 750upx;
			height: 100upx;
			display: flex;
			justify-content: center;
			align-items: center;
			color: #20bbc2;
		}
	}
</style>
