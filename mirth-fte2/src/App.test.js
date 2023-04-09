import { render, screen, fireEvent } from "@testing-library/react";
import App from "./App";
import TestRenderer from "react-test-renderer";

import AddPatient from "./components/AddPatient";

test("open add patient modal form", () => {
    render(<App />);
    fireEvent.click(screen.getByText("New Patient"));
    expect(screen.getByRole("dialog")).toHaveTextContent("New Patient");
});

test('renders a snapshot', () => {
    const tree = TestRenderer.create(<AddPatient/>).toJSON();    
    expect(tree).toMatchSnapshot();    
});