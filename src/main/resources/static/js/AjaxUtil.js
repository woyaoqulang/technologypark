var Ajax = (function () {
    var obj= {};

    function ajaxCommon(type,url,data,successBack,errorBack,cache,contentType){
        var ajaxContent={
            url:url,
            type:type,
            cache:false,
            contentType:"application/x-www-form-urlencoded; charset=utf-8",
            success:function(rtn){
                if (successBack) {
                    successBack(rtn);
                }
            },
            error:function(rtn){
                if (errorBack) {
                    errorBack(rtn);
                }
            }

        }
        if (data != undefined) {
            if (contentType && contentType == "json") {
                data = JSON.stringify(data);
                ajaxContent.contentType = "application/json;charset=utf-8";
            }
            ajaxContent.data = data;
        }
        if(cache){
            ajaxContent.cache = cache;
        }
    }
    obj.post = function (url, data, successBack,errorBack) {
        if (!data) {
            console.error("推荐使用get");
            return;
        }
        ajaxCommon("post", url, data, successBack, errorBack, undefined, undefined);
    }

    obj.get = function (url, successBack,errorBack) {
        ajaxCommon("post", url, undefined, successBack, errorBack, undefined, undefined);
    }

    obj.postJson = function (url, data, successBack,errorBack) {
        if (!data) {
            console.error("推荐使用get");
            return;
        }
        ajaxCommon("post", url, data, successBack, errorBack, undefined, "json");
    }


})();
