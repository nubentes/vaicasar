import styled from 'styled-components/native';

interface Props {
  color: boolean;
}

export const Container = styled.View`
  flex: 1;

  background-color: ${({ theme }) => theme.colors.white};
`;

export const Info = styled.View`
  flex: 1;

  margin-top: 20px;
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
  flex-direction: column;
`;

export const DateButton = styled.TouchableOpacity`
  width: 128px;
  margin-left: 10px;

  border-color: ${({ theme }) => theme.colors.black};
  border-width: 1px;
  border-style: solid;

  flex-direction: row;

  align-items: center;
  justify-content: center;
`;

export const StoreButton = styled.TouchableOpacity`
  width: 128px;
  margin-left: 10px;

  border-color: ${({ theme }) => theme.colors.black};
  border-width: 1px;
  border-style: solid;

  flex-direction: row;

  align-items: center;
  justify-content: center;
`;

export const Label = styled.Text`
  font-size: 16px;
  font-weight: 400;
  color: ${({ theme }) => theme.colors.black};
`;

export const Value = styled.Text<Props>`
  font-size: 16px;
  font-weight: 400;
  color: ${({ theme, color }) =>
    color ? theme.colors.black : theme.colors.gray};
`;

export const DescriptionLabel = styled.Text`
  font-size: 16px;
  font-weight: 400;
  color: ${({ theme }) => theme.colors.black};
`;

export const DescriptionInput = styled.TextInput`
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
