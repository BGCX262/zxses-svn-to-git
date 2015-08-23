/*--------------------qq onlin 自动浮动-----------------------*/
function Mouseclose(){document.getElementById('floatDivr').style.display='none';}
				window.onload = function(){
				var floatObjr = document.getElementById('floatDivr');
				Floaters.addItem(floatObjr,document.getElementById('wx_head').getBoundingClientRect().left + 1020,100);
				Floaters.sPlay();
				}
/*---------*/
var Floaters = {

 delta: 0.08,

 queue: null,

 collection: {},

 items: [],

 addItem: function(Obj,left,top,ani)
 {
 Obj.style['top'] = top + 'px';
 Obj.style['left'] = left + 'px';
 var newItem = { object:Obj, oleft:left, oTop:top };
 this.items[this.items.length] = newItem;
 this.delta = ani ? ani : this.delta;
 },

 sPlay: function()
 {
 this.collection = this.items;
 this.queue = setInterval('play()',10);
 }
}

function checkStandard()
{
 var scrollY;
 if (document.documentElement && document.documentElement.scrollTop)
 {
 scrollY = document.documentElement.scrollTop;
 }
 else if (document.body)
 {
 scrollY = document.body.scrollTop;
 }
 return scrollY;
}

function play()
{
 var diffY = checkStandard();
 for(var i in Floaters.collection)
 {
 var obj = Floaters.collection[i].object;
 var obj_y = Floaters.collection[i].oTop;
 var total = diffY + obj_y;
 if( obj.offsetTop != total)
 {
 var oy = (total - obj.offsetTop) * Floaters.delta;
 oy = ( oy>0?1:-1 ) * Math.ceil( Math.abs(oy) );
 obj.style['top'] = obj.offsetTop + oy + 'px';
 }else
 {
 clearInterval(Floaters.queue);
 Floaters.queue = setInterval('play()',10);
 }
 }
}