new Vue({
    el: '#lists',

    data: {
        currentUser: 1,
        results: []
    },

    created: function() {
        console.log("Created");
    },

    methods: {


        queryMax: function() {
            var self = this;
            axios.get('/stats', {

            }).then(function(response) {
                self.results = response.data;
            })
        },

        queryMin: function() {
            var self = this;
            axios.get('/min', {

            }).then(function(response) {
                self.results = response.data;
            })
        }



    }

});