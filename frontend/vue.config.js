module.exports = {
    devServer: {
        proxy: {'^/(api|foto)': { target: 'http://localhost:9090/', ws:true, secure:false},
        }
    }
}