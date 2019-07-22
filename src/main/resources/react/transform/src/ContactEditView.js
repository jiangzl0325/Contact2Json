import * as React from "react";
import "./App.css";
import { Input } from "antd";

const { TextArea } = Input;

export default class ContactEditView extends React.Component {
  render() {
    return (
      <TextArea
        disabled = {this.props.disabled}
        className="Home-content-left"
        rows={20}
        value={this.props.contactText}
        onChange={e => {
          this.props.contactChange(e.target.value);
        }}
      />
    );
  }
}
