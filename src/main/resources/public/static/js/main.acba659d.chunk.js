(window.webpackJsonp=window.webpackJsonp||[]).push([[0],{102:function(t,e,n){},177:function(t,e,n){"use strict";n.r(e);var a=n(0),o=n.n(a),s=n(16),i=n.n(s),c=(n(102),n(62),n(29)),r=(n(104),n(94)),l=n(23),u=n(24),h=n(27),d=n(25),p=n(28),g=(n(44),n(63),n(40)),m=g.a.TextArea,j=function(t){function e(){return Object(l.a)(this,e),Object(h.a)(this,Object(d.a)(e).apply(this,arguments))}return Object(p.a)(e,t),Object(u.a)(e,[{key:"render",value:function(){var t=this;return a.createElement(m,{disabled:this.props.disabled,allowClear:!0,defaultValue:'{"message_parent":{"parent_child":{"child_string":"child_string","child_integer":1}},"message_string":"message_string","message_integer":1}',className:"Home-content-left",rows:20,value:this.props.contactText,onChange:function(e){t.props.contactChange(e.target.value)}})}}]),e}(a.Component),f=g.a.TextArea,b=function(t){function e(){return Object(l.a)(this,e),Object(h.a)(this,Object(d.a)(e).apply(this,arguments))}return Object(p.a)(e,t),Object(u.a)(e,[{key:"render",value:function(){var t=this;return a.createElement(f,{disabled:this.props.disabled,allowClear:!0,className:"Home-content-right",rows:20,value:this.props.jsonText,onChange:function(e){t.props.jsonChange(e.target.value)}})}}]),e}(a.Component),C=n(93),T=n.n(C),w=function(t){function e(t){var n;return Object(l.a)(this,e),(n=Object(h.a)(this,Object(d.a)(e).call(this,t))).contactChange=function(t){n.setState({contactText:t})},n.jsonChange=function(t){n.setState({jsonText:t})},n.handleClick=function(t){n.setState({isloading:!0}),T.a.get(encodeURI("http://localhost:8080/contact2json?contact="+n.state.contactText)).then(function(t){console.log(t.data),n.setState({jsonText:t.data.content}),setTimeout(function(){n.setState({isloading:!1}),n.setState({jsonText:JSON.stringify(JSON.parse(n.state.jsonText),null,4)})},500)})},n.state={contactText:"class CityInfo{\n          /* \u57ce\u5e02id */\n          int cityId;\n          /* \u57ce\u5e02\u56fe\u7247url */\n          string imageUrl;\n          /* \u57ce\u5e02\u8be6\u60c5\u8df3\u8f6c\u94fe\u63a5 */\n          string districtUrl;\n          /* need to know \u8df3\u8f6c\u94fe\u63a5 */\n          string needToKnowUrl;\n      }\n      \n      class GetCitySimpleInfoResponseType{\n          BaijiCommonTypes.ResponseStatusType responseStatus;\n          BaijiIbuCommonTypes.ResponseHead responseHead;\n          /* \u56fe\u7247\u94fe\u63a5 */\n          list<CityInfo> infos;\n      }",jsonText:"",isloading:!1},n}return Object(p.a)(e,t),Object(u.a)(e,[{key:"render",value:function(){var t=this;return a.createElement("div",{className:"App"},a.createElement("p",{className:"App-header"},this.state.isloading?a.createElement(r.a,{tip:"Loading..."}):null,"\u5951\u7ea6\u8f6c\u6362mock\u62a5\u6587\u5de5\u5177"),a.createElement("body",{className:"Home-content"},a.createElement(j,{contactText:this.state.contactText,contactChange:this.contactChange}),a.createElement(c.a,{style:{margin:10},onClick:function(e){return t.handleClick(e)}},"\u8f6c\u6362"),a.createElement(b,{jsonText:this.state.jsonText,jsonChange:this.jsonChange})))}}]),e}(a.Component);Boolean("localhost"===window.location.hostname||"[::1]"===window.location.hostname||window.location.hostname.match(/^127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$/));i.a.render(o.a.createElement(w,null),document.getElementById("root")),"serviceWorker"in navigator&&navigator.serviceWorker.ready.then(function(t){t.unregister()})},44:function(t,e,n){},97:function(t,e,n){t.exports=n(177)}},[[97,1,2]]]);
//# sourceMappingURL=main.acba659d.chunk.js.map