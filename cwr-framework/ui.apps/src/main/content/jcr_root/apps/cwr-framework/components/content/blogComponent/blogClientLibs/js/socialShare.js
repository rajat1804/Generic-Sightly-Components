/*
    SocialShare - jQuery plugin
*/

(function ($) {

 $(document).ready(function(){
                $('.share').ShareLink({
                    title: $('#blogTitleId').val(),
                    text: $('#blogDescId').val(),
                    image: 'https://static.pexels.com/photos/27714/pexels-photo-27714.jpg',
                    url: 'http://www.gsmarena.com'
                });
                $('.counter').ShareCounter({
                    url: 'http://google.com/'
                });
     });
    function get_class_list(elem){
        if(elem.classList){
            return elem.classList;
        }else{
            return $(elem).attr('class').match(/\S+/gi);
        }
    }

    $.fn.ShareLink = function(options){
        var defaults = {
            title: '',
            text: '',
            image: '',
            url: window.location.href,
            class_prefix: 's_'
        };

        var options = $.extend({}, defaults, options);

        var class_prefix_length = options.class_prefix.length;

        var templates = {
            twitter: 'https://twitter.com/intent/tweet?url={url}&text={text}',
            pinterest: 'https://www.pinterest.com/pin/create/button/?media={image}&url={url}&description={text}',
           // facebook: 'https://www.facebook.com/sharer.php?s=100&p[title]={title}&p[summary]={text}&p[url]={url}&p[images][0]={image}',
            facebook: 'https://www.facebook.com/sharer/sharer.php?u={url}&title={title}&description={text}&caption=aaaaaaa',
            linkedin: 'https://www.linkedin.com/shareArticle?mini=true&url={url}&title={title}&summary={text}',
            plus: 'https://plus.google.com/share?url={url}'
        }

        function link(network){
            var url = templates[network];
            url = url.replace('{url}', encodeURIComponent(options.url));
            url = url.replace('{title}', encodeURIComponent(options.title));
            url = url.replace('{text}', encodeURIComponent(options.text));
            url = url.replace('{image}', encodeURIComponent(options.image));
            return url;
        }

        this.each(function(i, elem){
            var classlist = get_class_list(elem);
            for(var i = 0; i < classlist.length; i++){
                var cls = classlist[i];
                if(cls.substr(0, class_prefix_length) == options.class_prefix && templates[cls.substr(class_prefix_length)]){
                    var final_link = link(cls.substr(class_prefix_length));
                    $(elem).attr('href', final_link).click(function(){
                        var screen_width = screen.width;
                        var screen_height = screen.height;
                        var popup_width = screen_width - (screen_width*0.2);
                        var popup_height = screen_height - (screen_height*0.2);
                        var left = (screen_width/2)-(popup_width/2);
                        var top = (screen_height/2)-(popup_height/2);
                        var parameters = 'toolbar=0,status=0,width=' + popup_width + ',height=' + popup_height + ',top=' + top + ',left=' + left;
                        return window.open($(this).attr('href'), '', parameters) && false;
                    });
                }
            }
        });
    }

    $.fn.ShareCounter = function(options){
        var defaults = {
            url: window.location.href,
            class_prefix: 'c_',
            display_counter_from: 0
        };

        var options = $.extend({}, defaults, options);

        var class_prefix_length = options.class_prefix.length

        var social = {
            'twitter': twitter,
            'facebook': facebook,
            'linkedin': linkedin,
            'pinterest': pinterest
        }

        this.each(function(i, elem){
            var classlist = get_class_list(elem);
            for(var i = 0; i < classlist.length; i++){
                var cls = classlist[i];
                if(cls.substr(0, class_prefix_length) == options.class_prefix && social[cls.substr(class_prefix_length)]){
                    social[cls.substr(class_prefix_length)](options.url, function(count){
                        if (count >= options.display_counter_from){
                            $(elem).text(count);
                        }
                    })
                }
            }
        });

        function twitter(url, callback){
            $.ajax({
                type : 'GET',
                dataType : 'jsonp',
                url : 'https://twitter.com/intent/tweet',
                data : {'url': url}
            })
            .done(function(data){callback(data.count);})
            .fail(function(data){callback(0);})
        }

        function facebook(url, callback){
            $.ajax({
                type: 'GET',
                dataType: 'jsonp',
                url: 'https://api.facebook.com/restserver.php',
                data: {'method': 'links.getStats', 'urls': [url], 'format': 'json'}
            })
           
        }


        function linkedin(url, callback){
            $.ajax({
                type: 'GET',
                dataType: 'jsonp',
                url: 'https://www.linkedin.com/countserv/count/share',
                data: {'url': url, 'format': 'jsonp'}
            })
            .done(function(data){callback(data.count)})
            .fail(function(){callback(0)})
        }


        function pinterest(url, callback){
            $.ajax({
                type: 'GET',
                dataType: 'jsonp',
                url: 'https://api.pinterest.com/v1/urls/count.json',
                data: {'url': url}
            })
            .done(function(data){callback(data.count)})
            .fail(function(){callback(0)})
        }

    }

})(jQuery);
