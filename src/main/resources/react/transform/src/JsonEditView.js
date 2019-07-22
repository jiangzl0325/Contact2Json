import * as React from "react";
import "./App.css";
import { Input } from "antd";

const { TextArea } = Input;

export default class JsonEditView extends React.Component {
  render() {
    return (
      <TextArea
        disabled={this.props.disabled}
        allowClear={true}
        className="Home-content-right"
        rows={20}
        value={this.props.jsonText}
        onChange={e => {
          this.props.jsonChange(e.target.value);
        }}
      />
    );
  }
}
