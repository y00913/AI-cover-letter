const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  configureWebpack: {
    devServer: {
      hot: false,
      client: {
        webSocketURL: false, // 웹소켓 연결 비활성화
      }
    }
  }
})
