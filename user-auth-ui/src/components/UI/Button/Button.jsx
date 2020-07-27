import React from "react";

const button = (props) => (
	<div className={props.divClasses}>
		<button
			disabled={props.disabled}
			className={props.btnClasses}
			onClick={props.clicked}
		>
			{props.children}
		</button>
	</div>
);

export default button;
