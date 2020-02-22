<template>
	<view class="content">
		
		<view class="top">
			
		</view>
		
		<view class="register-item">
			<view class="register-item-cont">
				<view class="register-lable">
					开户行:
				</view>
				<view class="input-box">
					<input type="text" v-model="bankName" placeholder="开户行" />
				</view>
			</view>
		</view>
		
		<view class="register-item">
			<view class="register-item-cont">
				<view class="register-lable">
					开户名:
				</view>
				<view class="input-box">
					<input type="text" v-model="username" placeholder="开户名" />
				</view>
			</view>
		</view>
		
		<view class="register-item">
			<view class="register-item-cont">
				<view class="register-lable">
					卡号:
				</view>
				<view class="input-box">
					<input type="text" v-model="carNumber" placeholder="卡号:" />
				</view>
			</view>
		</view>
		
		<view class="register-item">
			<view class="register-item-cont">
				<view class="register-lable">
					金额:
				</view>
				<view class="input-box">
					<input type="text"  v-model="tixian"  @input='change' placeholder="提现金额" />
				</view>
				
			</view>
		</view>
		
		<view class="msg" style="color: #FFFFFF;">
			<view class="msg-item">手续费：{{shouxufei}}</view>
			<view class="msg-item">最多能提：{{max}}</view>
		</view>
		
		<view class="sub-jg"></view>
		
		<view class="sub-item">
			<button type="primary" class="submit-btn" @tap="ti()">申请提现</button>
		</view>
	</view>
</template>

<script>
	var that;
	export default {
		data() {
			return {
				money:0,
				max:0,
				tixian:0,
				shouxufei:0,
				bankName:'',
				username:'',
				carNumber:''
			}
		},
		
		onLoad(options) {
			that = this;
			that.money = options.money;
			that.max =Math.floor(that.money / 1.02);
		},
		
		methods: {
			change(event){
				that.tixian = event.target.value;
				if(that.tixian > that.max){
					uni.showModal({
						content: '超出最大提现金额',
						showCancel: false
					});
					that.tixian = Math.floor(that.tixian / 10);
				}
				that.shouxufei = Math.ceil(that.tixian * 0.02);
			},
			
			ti(){
				that.$utils.http(that.$api.withdraw_create,{
					tixian:that.tixian,
					shouxu:that.shouxufei,
					bankName:that.bankName,
					username:that.username,
					carNumber:that.carNumber
				},function(res){
					uni.showModal({
						content: '提交成功，等待管理员审核打款',
						showCancel: false,
						success() {
							uni.navigateBack({
								
							});
						}
					});
				})
			}
			
		}
	}
</script>


<style lang="scss" scoped>
	.content {

		background-image: url(https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1428254237,2071131444&fm=26&gp=0.jpg);
		
		.top {
			height: 220upx;
			width: 750upx;
			display: flex;
		}
		
		.register-item{
			width: 750upx;
			height: 100upx;
			margin-top: 5upx;
			display: flex;
			justify-content: center;
			align-items: flex-start;
			
			.register-item-cont{
				width: 600upx;
				height: 85upx;
				border-bottom: 3upx solid #8F8F94;
				display: flex;
				justify-content: space-between;
				flex-direction: row;
				font-size: 28upx;
				color: #F2F2F2;
				
				.register-lable{
					width: 130upx;
					height: 85upx;
					display: flex;
					align-items: center;
					justify-content: flex-end;
				}
				
				.input-box{
					width: 455upx;
					height: 85upx;
					
					input{
						width: 455upx;
						height: 85upx;
					}
				}
				
				.action{
					width: 200upx;
					height: 85upx;
					display: flex;
					align-items: center;
					
					.send_code{
						font-size: 28upx;
						width: 200upx;
						height: 70upx;
						background: rgba(255,255,255,0);
						border: 2upx solid #C0C0C0;
					}
				}
				
				
			}
		}
		
		
		.sub-jg{
			width: 750upx;
			height: 30upx;
		}
		
		.sub-item{
			height: 100upx;
			width: 750upx;
			color: #FAB116;
			display: flex;
			justify-content: center;
			align-items: center;
			font-size: 30upx;
		}
		
		.submit-btn{
			height: 80upx;
			width: 500upx;
			background-color: #FAB116;
			color: #000000;
			display: flex;
			align-items: center;
			justify-content: center;
			font-size: 30upx;
		}
		
	}
</style>
Í