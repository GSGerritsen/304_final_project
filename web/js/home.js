const config = {
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
    }
};

new Vue({
    el: '#lists',

    data: {
        currentUser: 1,
        search: '',
        book: false,
        comic: false,
        movie: false,
        tv: false,
        mediaTitle: false,
        max: false,
        min: false,
        media: [],
        selectedResult: 0
    },

    created: function() {
        console.log("created!");
    },

    methods: {
        submitForm: function() {
           console.log("fuck");
           var self = this;
           axios.get('/search_media', {
               params: self.assembleFormData()
           }).then(function(response) {
               if (response.status === 200) {
                   self.media = response.data;
               }
           })

        },

        setIndex: function(event) {
            var message = event.currentTarget;
            var re = /index">(\d+)/;
            var reMatch = message.innerHTML.match(re);
            var index = reMatch[1];
            this.selectedResult = parseInt(index);
        },

        addToViewLater: function() {
            var self = this;
            axios.post('/view_later', {
                uid: 1,
                mid: this.media[this.selectedResult].id
            }).then(function(response) {
                if (response.status === 200) {
                    console.log("Success adding to view later")
                }
            })
        },

        assembleFormData: function() {
            obj = {};
            // Pull in all the bound data from the search component
            obj.search      = this.search;
            obj.book        = this.book;
            obj.comic       = this.comic;
            obj.movie       = this.movie;
            obj.tv          = this.tv;
            obj.mediaTitle  = this.mediaTitle;
            obj.max         = this.max;
            obj.min         = this.min;
            return obj;
        },


    }

});