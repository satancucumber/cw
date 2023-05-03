const { defineConfig } = require('@vue/cli-service')
module.exports = {
  transpileDependencies: [
        "@splidejs"
      ]
}
//const { defineConfig } = require('@vue/cli-service')
//module.exports = {
//  devServer: {
//      clientLogLevel: 'info',
//      host: '0.0.0.0',
//    // https:true,
//      port: 6103,
//      client: {
//        webSocketURL: 'ws://0.0.0.0:6103/ws',
//      },
//      headers: {
//        'Access-Control-Allow-Origin': '*',
//      }
//  },
//  transpileDependencies: [
//      "@splidejs"
//    ]
//}
