use(function () {

var proposerMap = [];

var tempProposerMap = this.nodeName;
log.error("***"+tempProposerMap);
try{
if (typeof tempProposerMap === 'string' || tempProposerMap instanceof String){
var item = tempProposerMap;
item = JSON.parse(item);
    if(item.hasOwnProperty("multifield")){
                for(var cnt in item.multifield) {
                    item.multifield[cnt] = JSON.parse(item.multifield[cnt]);
                }
    }

proposerMap.push(item)
}
}catch(e){
for(var i in tempProposerMap) {
var item = tempProposerMap[i];
item = JSON.parse(item);
 if(item.hasOwnProperty("multifield")){
                for(var cnt in item.multifield) {
                    item.multifield[cnt] = JSON.parse(item.multifield[cnt]);
                }
    }
proposerMap.push(item)
}
}

return {
proposerObjs : proposerMap
};
});
