import * as mysql from 'mysql2/promise'

const access = {
    host: '127.0.0.1',
    port: 3307,
    user: 'lisi',
    database: 'OfficialWebsite',
    password: 'Password1='
}

const conn = mysql.createPool(access);


// 定义并导出操作数据库的函数
export const sql = async ({ query, values }) => {
    const [rows] = await conn.query(query, values)
    return rows
}
