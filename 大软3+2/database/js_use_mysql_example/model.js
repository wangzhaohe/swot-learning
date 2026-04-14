import { sql } from "./dbase.js";
export const create = async (data) => {

    const result = await sql({
        query: `INSERT INTO ContentCategory (title, owner) VALUES (?, ?)`,
        values: [data.title, data.owner]
    });

    // console.log("result:", result);

    if (result.affectedRows === 1) {

        const insertedId = result.insertId;
        const insertedRow = await sql({
            query: 'SELECT * FROM ContentCategory WHERE id = ?',
            values: [insertedId],
        });
        // console.log("insertedRow:", insertedRow);
        return insertedRow.length === 1 ? (insertedRow[0]) : null;
    }
    else {
        return null;
    }
}
export const update = async (id, data) => {

    const result = await sql({
        query: `
            UPDATE ContentCategory SET
                title = ?,
                owner = ?
            WHERE id = ?
        `,
        values: [ data.title, data.owner, id ],
    });

    console.log("update result:", result);
    return await detail(id);
}
export const detail = async (id) => {

    const result = await sql({
        query: 'SELECT * FROM ContentCategory WHERE id = ?',
        values: [id],
    });

    return result.length === 1 ? (result[0]) : null;
}
export const read = async () => {

    const result = await sql({
        query: 'SELECT id, title, owner FROM ContentCategory'
    });
    return result;
}
export const remove = async (id) => {

    const result = await sql({
        query: 'DELETE FROM ContentCategory WHERE id = ?',
        values: [id],
    });

    console.log('remove result:', result);
    return result.affectedRows === 1 ? 'success' : null;
}

// delete 是 javascript 的一个关键字，避免作为函数名使用。
