import * as model from "./model.js";
export const create = async (body) => {
    try {
        const result = await model.create(body);
        // console.log("result:", result);
        return {
            code: 0,
            data: result
        }
    }
    catch (error) {
        console.log('error:', error);
    }
}

// Example:
// const res = await create({title: "编程技术", owner: "Swot"});
// console.log('res:', res);
export const update = async (id, body) => {
    try {
        const result = await model.update(
            id,
            {
                title: body.title,
                owner: body.owner
            }
        );
        return {
            code: 0,
            data: result
        }
    }
    catch (error) {
        console.log(error);
    }
}

// Example:
// const res = await update(115, { title: "zzz", owner: "ZZZ" });
// console.log('res:', res);
export const detail = async (id) => {
    try {
        const result = await model.detail(id);
        return {
            code: 0,
            data: result
        }
    }
    catch (error) {
        console.log(error);
    }
}

// Example:
// const res = await detail(115)
// console.log('res:', res);
export const read = async () => {
    try {
        const result = await model.read();
        return {
            code: 0,
            data: result
        }
    }
    catch (error) {
        console.log(error);
    }
};

// Example:
// const res = await read()
// console.log('res:', res);
export const remove = async (id) => {
    try {
        const result = await model.remove(id);
        return {
            code: 0,
            data: result
        }
    }
    catch (error) {
        console.log(error);
    }
}

// Example:
// const res = await remove(115)
// console.log('res:', res);


