use(function () {

	var proposerMap = [];

	var tempProposerMap = properties.socialMap;


	try{
		if (typeof tempProposerMap === 'string' || tempProposerMap instanceof String){
			var item = tempProposerMap;
			 item = JSON.parse(item);
			if(item.hasOwnProperty("multivalues")){
                for(var cnt in item.multivalues) {
                    item.multivalues[cnt] = JSON.parse(item.multivalues[cnt]);
                }
            }
			proposerMap.push(item)
		}


	}catch(e){
        console.log("-->11");
		for(var i in tempProposerMap) {
             console.log("-->22");
			var item = tempProposerMap[i];
             console.log("-->33");
            console.log("----->"+item);
			 item = JSON.parse(item);
			if(item.hasOwnProperty("multivalues")){
                for(var cnt in item.multivalues) {
                    item.multivalues[cnt] = JSON.parse(item.multivalues[cnt]);
                }
            }
			proposerMap.push(item)
		}
	}

		return {
			proposerObjs : proposerMap

		};
	});