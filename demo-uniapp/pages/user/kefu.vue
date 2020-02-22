<template>
	<view class="content">
		<top></top>
		<cnav text='首页-商品行情' :showLeft='true'></cnav>
		<view class="list-box">
			<web-view :webview-styles="webviewStyles" :src='url'></web-view>
		</view>
	</view>
</template>

<script>
	var that;
	export default {
		data() {
			return {
				url:''
			}
		},
		onLoad() {
			that = this;
			that.getData();
		},
		methods: {
			getData(){
				that.$utils.http(that.$api.customer_service_url,function(res){
					that.url = res.customer_service;
				});
			}
		}
	}
</script>

<style>

</style>
