import { userName } from "./login.js";
import { verifyDOMElementExisteOrError } from "../verify/existDomElement.js";
import { tryPostCardsData } from "../other/postCardData.js";
const containerUserName = document.getElementById("UserName");
verifyDOMElementExisteOrError(containerUserName);
containerUserName.textContent = userName;
tryPostCardsData();
