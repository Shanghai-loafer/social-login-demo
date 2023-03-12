import React from 'react';
import ReactDOM from 'react-dom/client';
import './component/system/index.css';
import reportWebVitals from './component/system/reportWebVitals';
import Blog from "./component/domain/Blog";

const root = ReactDOM.createRoot(
    document.getElementById('root') as HTMLElement
);
root.render(
    <React.StrictMode>
        <Blog/>
    </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
