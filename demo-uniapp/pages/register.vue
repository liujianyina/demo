<template>
	<view class="content">
		
		<view class="top">
			
		</view>
		
		<view class="register-item">
			<view class="register-item-cont">
				<view class="register-lable">
					Name:
				</view>
				<view class="input-box">
					<input type="text" v-model="name" placeholder="请输入真实姓名/Name" />
				</view>
			</view>
		</view>
		
		<view class="register-item">
			<view class="register-item-cont">
				<view class="register-lable">
					Phone:
				</view>
				<view class="input-box">
					<input type="text" v-model="phone" placeholder="11位手机号码" />
				</view>
			</view>
		</view>
		
		<!-- <view class="register-item">
			<view class="register-item-cont">
				<view class="register-lable">
					Code:
				</view>
				<view class="input-box" style="width: 255upx;">
					<input type="text" v-model="code" placeholder="短信验证码" />
				</view>
				<view class="action">
					<button type="primary" class="send_code" @tap="send_code()">获取验证码</button>
				</view>
			</view>
		</view> -->
		
		
		<view class="register-item">
			<view class="register-item-cont">
				<view class="register-lable">
					Password:
				</view>
				<view class="input-box">
					<input type="password" v-model="password1" placeholder="请设置交易密码" />
				</view>
			</view>
		</view>
		
		
		<view class="register-item">
			<view class="register-item-cont">
				<view class="register-lable">
					Password:
				</view>
				<view class="input-box">
					<input type="password" v-model="password2" placeholder="请确认交易密码" />
				</view>
			</view>
		</view>
		
		<view class="register-item">
			<view class="register-item-cont">
				<view class="register-lable">
					Invitarion:
				</view>
				<view class="input-box">
					<input type="text" v-model="invitarion" placeholder="邀请码" />
				</view>
			</view>
		</view>
		
		<view class="sub-jg"></view>
		
		<view class="sub-item">
			<button type="primary" class="submit-btn" @tap="register()">注册</button>
		</view>
		<view class="sub-item" style="margin-top: 30upx;">
			<navigator url="/pages/login">已有账号，去登陆</navigator>
		</view>
	</view>
</template>

<script>
	var that;
	export default {
		data() {
			return {
				name:'',
				phone:'',
				code:'0000',
				password1:'',
				password2:'',
				invitarion:''
			}
		},
		
		onLoad() {
			that = this;
		},
		
		methods: {
			
			send_code(){
				if(that.phone.length != 11){
					uni.showToast({
					    title: '请输入正确的手机号',
					    duration: 2000
					});
				}else{
					that.$utils.http(that.$api.send_code,{
						phone:that.phone
					},function(res){
						uni.showToast({
						    title: '发送成功，请注意查收',
						    duration: 2000
						});
					});
				}
			},
			
			register(){
				
				that.$utils.http(that.$api.register,{
					username:that.name,
					phone:that.phone,
					password:that.password1,
					invitarion:that.invitarion,
					code:that.code
				},function(res){
					uni.showToast({
					    title: '注册成功！',
					    duration: 2000
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
