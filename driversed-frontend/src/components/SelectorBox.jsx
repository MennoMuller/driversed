import React from "react";

function SelectorBox(props) {
  return (
    <select
      name={props.name}
      id={props.name}
      value={props.value}
      onChange={(e) => {
        props.setValue(e.target.value);
      }}
    >
      <option value="0">Select {props.name}</option>
      {props.list.map((item) => (
        <option key={item.id} value={item.id}>
          {item.name}
        </option>
      ))}
    </select>
  );
}
export default SelectorBox;
