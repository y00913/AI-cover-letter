const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    hot: false,       // HMR 비활성화
    liveReload: false // 라이브 리로드 비활성화
  }
})
