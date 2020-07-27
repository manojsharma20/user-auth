import React, { Component } from "react";
import Aux from "../../../hoc/auxilary/Auxilary";

class NotFound extends Component {
	constructor(props) {
		super(props);
		this.state = {};
	}
	render() {
		return (
			<Aux>
				<div className="row">
					<div className="col-md-12">
						<h1>Not Found.</h1>
					</div>
				</div>
			</Aux>
		);
	}
}

export default NotFound;
