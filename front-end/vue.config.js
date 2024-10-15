module.exports = {
  chainWebpack: config => {
    config.plugin('html').tap(args => {
      args[0].meta = { charset: 'UTF-8' };
      return args;
    });
  }
};