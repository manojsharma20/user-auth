import React, { Component } from "react";
import { Route, Switch, withRouter, Redirect } from "react-router-dom";
import { connect } from "react-redux";

import Layout from "./hoc/layout/Layout";
import Dashboard from "./containers/pages/authenticated/dashboard/dashboard";
import Auth from "./containers/pages/authenticated/auth/auth";
import Logout from "./containers/pages/authenticated/auth/logout/logout";
import Home from "./containers/pages/home/home";
import NotFound from "./containers/pages/not-found/not-found";

import * as actions from "./store/actions/index";
import "bootstrap/dist/css/bootstrap.min.css";

class App extends Component {
	componentDidMount() {
		this.props.onTryAutoSignup();
	}

	state = {
		isAuthenticated: false,
	};

	render() {
		let routes = (
			<Switch>
				<Route path="/auth" component={Auth} />
				<Route path="/" component={Home} exact />
				<Route component={NotFound} />
			</Switch>
		);

		if (this.props.isAuthenticated) {
			routes = (
				<Switch>
					<Route path="/logout" component={Logout} />
					<Route path="/auth" component={Dashboard} />
					<Route path="/dashboard" component={Dashboard} />
					<Route component={NotFound} />
				</Switch>
			);
		}
		return (
			<div id="wrapper">
				<Layout>{routes}</Layout>
			</div>
		);
	}
}

const mapStateToProps = (state) => {
	return {
		isAuthenticated: state.auth.token !== null,
	};
};

const mapDispatchToProps = (dispatch) => {
	return {
		onTryAutoSignup: () => dispatch(actions.authCheckState()),
	};
};

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(App));
