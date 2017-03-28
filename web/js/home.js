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
        creator: false,
        mediaTitle: false,
        max: false,
        min: false,
        avgMax: false,
        avgMin: false
    },

    created: function() {
        console.log("created!");
    },

    methods: {
        submitForm: function() {
           var self = this;
           axios.get('/search_media', {
               params: self.assembleFormData()
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
            obj.creator     = this.creator;
            obj.mediaTitle  = this.mediaTitle;
            obj.max         = this.max;
            obj.min         = this.min;
            obj.avgMax      = this.avgMax;
            obj.avgMin      = this.avgMin;

            return obj;
        }

    }

});