import { render } from "@testing-library/react"
import { BrowserRouter, MemoryRouter, Routes, Route } from "react-router-dom"
// import { ContextProvider } from "../Components/utils/global.context"
// import Home from "../Routes/Home"
// import Detail from "../Routes/Detail"
// import Login from "../Routes/Login"
// import App from "../App"
import { LoginProvider} from '../hooks/useLogin'


const renderWithContext = (ui)=>{
    return render(
        <BrowserRouter>
            <LoginProvider>   
                {ui}
            </LoginProvider>
        </BrowserRouter>
    )
}

//Only for testing individual routes as /dentist/:id
export const renderWithRouter = (ui, path='/') => {
    window.history.pushState({}, 'Test page')

    return render(
        <MemoryRouter initialEntries={[path]}>
            <LoginProvider>
            <Routes>
                <Route index path={path} element={ui}/>
            </Routes>
            </LoginProvider>
        </MemoryRouter>
    )
}

export * from "@testing-library/react"
export {renderWithContext as render}  