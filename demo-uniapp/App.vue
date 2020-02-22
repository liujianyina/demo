<script>
	export default {
		onLaunch: function() {
			console.log('App Launch')
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style>
	/*每个页面公共css */
	* {
		padding: 0;
		margin: 0;
	}
	
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		height: 100vh;
		width: 750upx;
	}
	
	.list-box {
		display: flex;
		flex-direction: column;
		flex-grow: 1;
		height: 100%;
	}
	
	.list-item {
		border-bottom: 2upx solid #F3F3F3;
		display: flex;
		width: 100%;
	}
</style>
