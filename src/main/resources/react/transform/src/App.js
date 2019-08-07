import * as React from "react";
import "./App.css";
import { Button, Spin } from "antd";
import ContactEditView from "./ContactEditView";
import JsonEditView from "./JsonEditView";
import axios from 'axios'


export default class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      contactText:
        `class CityInfo{
          /* 城市id */
          int cityId;
          /* 城市图片url */
          string imageUrl;
          /* 城市详情跳转链接 */
          string districtUrl;
          /* need to know 跳转链接 */
          string needToKnowUrl;
      }
      
      class GetCitySimpleInfoResponseType{
          BaijiCommonTypes.ResponseStatusType responseStatus;
          BaijiIbuCommonTypes.ResponseHead responseHead;
          /* 图片链接 */
          list<CityInfo> infos;
      }`,
      jsonText: "",
      isloading: false
    };
  }

  contactChange = e => {
    this.setState({ contactText: e });
  };

  jsonChange = e => {
    this.setState({ jsonText: e });
  };

  handleClick = e => {
    this.setState({ isloading: true });

    axios.get(encodeURI("http://localhost:8080/contact2json?contact="+this.state.contactText)).then((response) => {
      console.log(response.data);
      this.setState({jsonText:response.data.content})
      setTimeout(() => {
        this.setState({ isloading: false });
        this.setState({
          jsonText: JSON.stringify(JSON.parse(this.state.jsonText), null, 4)
        });
      }, 500);
    });
    
  };

  render() {
    return (
      <div className="App">
        <p className="App-header">
          {this.state.isloading ? <Spin tip="Loading..." /> : null}
          契约转换mock报文工具
        </p>

        <body className="Home-content">
          <ContactEditView
            // disabled={this.state.isloading}
            contactText={this.state.contactText}
            contactChange={this.contactChange}
          />
          <Button style={{ margin: 10 }} onClick={e => this.handleClick(e)}>
            转换
          </Button>
          <JsonEditView
            // disabled={this.state.isloading}
            jsonText={this.state.jsonText}
            jsonChange={this.jsonChange}
          />
        </body>
      </div>
    );
  }
}
