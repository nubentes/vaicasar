import styled from 'styled-components/native';

export const Container = styled.View`
  flex: 1;
`;

export const Info = styled.View`
  flex: 1;

  padding: 0px 24px;
`;

export const Button = styled.TouchableOpacity`
  position: absolute;
  left: 10px;
  top: 30px;
`;

export const Row = styled.View`
  flex-direction: row;

  align-items: center;

  margin: 10px;
`;

export const Column = styled.View`
  padding: 0px 24px;
  flex-direction: column;
`;

export const Label = styled.Text`
  font-size: 16px;
  font-weight: 400;
  color: ${({ theme }) => theme.colors.black};
`;

export const Value = styled.Text`
  font-size: 16px;
  font-weight: 400;
  color: ${({ theme }) => theme.colors.black};
`;

export const Description = styled.Text`
  font-size: 16px;
  font-weight: 400;
  color: ${({ theme }) => theme.colors.black};
`;

export const Input = styled.TextInput`
  width: 250px;
  height: 40px;

  border-radius: 6px;
  margin-top: 20px;

  border-width: 1px;
  border-style: solid;
  border-color: ${({ theme }) => theme.colors.black};

  font-size: 16px;
  color: ${({ theme }) => theme.colors.black};

  padding-left: 10px;
`;

export const SaveButton = styled.TouchableOpacity`
  width: 100px;
  height: 40px;

  background-color: ${({ theme }) => theme.colors.red};

  align-items: center;
  justify-content: center;

  border-radius: 6px;

  margin-top: 10px;

  align-self: center;
`;
