const config = {
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
    }
};

new Vue({
    el: '#lists',

    data: {
        currentUser: 1,
        viewLater: [],
        selectedResult: 0
    },

    created: function() {
        var self = this;
        axios.get('/view_later', {
            params: {
                requester: 1
            }
        }).then(function(response) {
            self.viewLater = response.data;
        })
    },

    methods: {
        setIndex: function(event) {
            var message = event.currentTarget;
            var re = /index">(\d+)/;
            var reMatch = message.innerHTML.match(re);
            var index = reMatch[1];
            console.log(index);
            this.selectedResult = parseInt(index);
        },

        removeFromViewLater: function() {
            var self = this;
            axios.post('/remove_view_later', {
                uid: 1,
                mid: this.viewLater[this.selectedResult].rating
            }).then(function(response) {
                if (response.status === 200) {
                    console.log("Success removing from view later")
                    axios.get('/view_later', {
                        params: {
                            requester: 1
                        }
                    }).then(function(response) {
                        self.viewLater = response.data;
                    })
                }
            })
        }



    }

});