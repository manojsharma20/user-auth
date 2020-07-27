import axios from "axios";

import * as actionTypes from "./actionTypes";

export const authStart = () => {
	return {
		type: actionTypes.AUTH_START,
	};
};

export const authSuccess = (token, userId) => {
	return {
		type: actionTypes.AUTH_SUCCESS,
		idToken: token,
		userId: userId,
	};
};

export const authFail = (error) => {
	return {
		type: actionTypes.AUTH_FAIL,
		error: "Invalid credentials",
	};
};

export const logout = () => {
	localStorage.removeItem("token");
	localStorage.removeItem("expirationDate");
	localStorage.removeItem("userId");
	return {
		type: actionTypes.AUTH_LOGOUT,
	};
};

export const checkAuthTimeout = (expirationTime) => {
	return (dispatch) => {
		setTimeout(() => {
			dispatch(logout());
		}, expirationTime * 1000);
	};
};

export const auth = (email, password) => {
	return (dispatch) => {
		dispatch(authStart());
		var config = {
			method: "post",
			url:
				"http://localhost:8080/userauth/oauth/token?grant_type=password&" +
				"username=" +
				email +
				"&password=" +
				password,
			headers: {
				"Content-Type": "application/x-www-form-urlencoded",
				Authorization: "Basic " + btoa("user-auth:manoj"),
			},
		};

		axios(config)
			.then((response) => {
				const expirationDate = new Date(
					new Date().getTime() + response.data.expires_in * 1000
				);
				console.log(response);
				localStorage.setItem("token", response.data.access_token);
				localStorage.setItem("expirationDate", expirationDate);
				localStorage.setItem("userId", email);
				dispatch(authSuccess(response.data.access_token, email));
				dispatch(checkAuthTimeout(response.data.expires_in));
				dispatch(setAuthRedirectPath("/dashboard"));
			})
			.catch((err) => {
				dispatch(authFail(err));
			});
	};
};

export const setAuthRedirectPath = (path) => {
	return {
		type: actionTypes.SET_AUTH_REDIRECT_PATH,
		path: path,
	};
};

export const authCheckState = () => {
	return (dispatch) => {
		const token = localStorage.getItem("token");
		if (!token) {
			dispatch(logout());
		} else {
			const expirationDate = new Date(localStorage.getItem("expirationDate"));

			if (expirationDate <= new Date()) {
				dispatch(logout());
			} else {
				const userId = localStorage.getItem("userId");
				dispatch(authSuccess(token, userId));
				dispatch(
					checkAuthTimeout(
						(expirationDate.getTime() - new Date().getTime()) / 1000
					)
				);
			}
		}
	};
};
