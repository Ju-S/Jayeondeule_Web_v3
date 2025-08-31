import React from "react";
import ReactDOM from "react-dom/client";
import {Provider} from "react-redux";
import {store} from "./store/indexStore.js";
import App from "./App.jsx";
import 'bootstrap/dist/css/bootstrap.min.css';
import './index.css';
import {QueryClientProvider, QueryClient} from "@tanstack/react-query";

const queryClient = new QueryClient();

ReactDOM.createRoot(document.getElementById("root")).render(
    // <React.StrictMode>
    <Provider store={store}>
        <QueryClientProvider client={queryClient}>
            <App/>
        </QueryClientProvider>
    </Provider>
    // </React.StrictMode>
);
