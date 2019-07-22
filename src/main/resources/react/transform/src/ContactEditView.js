import * as React from "react";
import "./App.css";
import { Input } from "antd";

const { TextArea } = Input;

export default class ContactEditView extends React.Component {
  render() {
    return (
      <TextArea
        disabled = {this.props.disabled}
        allowClear = {true}
        defaultValue = '{"message_parent":{"parent_child":{"child_string":"child_string","child_integer":1}},"message_string":"message_string","message_integer":1}'
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
