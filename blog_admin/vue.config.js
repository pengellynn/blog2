const path = require('path')

function resolve (dir) {
  return path.join(__dirname, dir)
}
module.exports = {
  lintOnSave: true,
  chainWebpack: (config) => {
    config.resolve.alias
      .set('@', resolve('src'))
      .set('@assets', resolve('src/assets'))
  },
  devServer: {
  //  open: process.platform === 'darwin',
    host: 'localhost',
    port: 8082,
    https: false,
    hotOnly: false,
    proxy: null, // 设置代理
    before: app => {}
  }
}
