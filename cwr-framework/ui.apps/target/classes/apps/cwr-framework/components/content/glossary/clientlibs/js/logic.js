use(function () {
    //backgroundImage = granite.resource.properties["backgroundImage"];
	//var bgImage = properties.get("backgroundImage");
    var bgImage = granite.resource.properties["backgroundImage"];
    var bgUrl = "background: url('"+ bgImage + "')";
    return {
        bgUrl: bgUrl
    };
});