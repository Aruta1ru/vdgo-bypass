const path = require('path');
const VueLoaderPlugin = require('vue-loader/lib/plugin');
const PrerenderSPAPlugin = require('prerender-spa-plugin')

module.exports = {
  entry: path.join(__dirname, 'src', 'main', 'resources', 'js', 'main.js'),
  module: {
    rules: [
      {
        test: /\.js$/,
        exclude: /(node_modules|bower_components)/,
        use: {
          loader: 'babel-loader',
          options: {
            presets: ['@babel/preset-env']
          }
        }
      },
      {
        test: /\.vue$/,
        loader: 'vue-loader'
      },

      {
              test: /\.css$/,
              use: [
              'vue-style-loader',
              'css-loader'
              ]
      }
    ]
  },

  plugins: [
    new VueLoaderPlugin(),
    new PrerenderSPAPlugin({
              // Required - The path to the webpack-outputted app to prerender.
              staticDir: path.join(__dirname, 'dist'),
              // Required - Routes to render.
              routes: [ '/' ],
            })
  ],
  resolve: {
      modules: [
          path.join(__dirname, 'src', 'main', 'resources', 'js'),
          path.join(__dirname, 'node_modules'),
      ],
  }
}