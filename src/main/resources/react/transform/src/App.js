import * as React from "react";
import "./App.css";
import { Button, Spin } from "antd";
import ContactEditView from "./ContactEditView";
import JsonEditView from "./JsonEditView";

export default class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = { contactText: "", jsonText: "", isloading: false };
  }

  contactChange = e => {
    this.setState({ contactText: e });
  };

  jsonChange = e => {
    this.setState({ jsonText: e });
  };

  handleClick = e => {
    this.setState({ isloading: true });
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
