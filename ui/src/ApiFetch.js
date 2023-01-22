import React, { useState, useEffect } from "react";

// TODO tsxの形式に書き直してあげる

export const ApiFetch = () => {
    const [stones, setStone] = useState([]);

    useEffect(() => {
        // APIをfetchする(呼び出す)
        fetch("http://localhost:8080/api/fetch", { method: "GET" })
        // レスポンスのデータ形式をjsonに設定
        .then((res) => res.json())
        // APIから渡されるレスポンスデータ(data)をstateにセットする
        .then((data) => {
            setStone(data);
        });
    }, []);

    return (
        <div>
            <ul>
                <li>{stones.month}</li>
                <li>{stones.color}</li>
                <li>{stones.name}</li>
            </ul>
        </div>
    );
};
